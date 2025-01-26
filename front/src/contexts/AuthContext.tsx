import { createContext, ReactNode, FC, useContext, useState, useEffect, Dispatch, SetStateAction} from "react";
import axios from "axios";
import { LoginData } from "../types/LoginData";
import { SignupData } from "../types/SignupData";
import { toast } from "react-toastify";
import { useLoading } from "./loadingContext";
import { User } from "../types/Code";

interface AuthContextType {
  login: (user: LoginData) => Promise<boolean|undefined>;
  signup: (user:SignupData)=> Promise<boolean>;
  user: User|undefined;
  checkAuth: ()=> Promise<boolean|undefined>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

const AuthProvider: FC<AuthProviderProps> = ({ children }) => {
  const authApi = axios.create({ baseURL: `${import.meta.env.VITE_SERVER_URL}/auth` });
  const userApi = axios.create({baseURL: `${import.meta.env.VITE_SERVER_URL}/users`});
   const {setIsLoading} = useLoading();
   const [user, setUser] = useState<User|undefined>(undefined);
 
  const checkAuth = async () => {
    try {
     
      const response = await userApi.get('/checkLoggedIn', { withCredentials: true });
      if (response.status === 200) {
       return true;
      }
    } catch (error) {
      console.error('Error checking authentication', error);
      return false;
    }
  };

  const refreshToken = async ()=> {
    await authApi.post("/refresh-token");
  }

  const getUser = async () => {
    try {
     
      const {data,status} = await userApi.get('/me', { withCredentials: true });
      console.log(data)
      if (status === 200) {
        setUser(data);
       return true;
      }


    } catch (error) {
      console.error('Error checking authentication', error);
      return false;
    }
  };

   useEffect( () => {
   checkAuth();
  getUser();
refreshToken();

const interval = setInterval(refreshToken, 1000 * 60 * 10);

return ()=> clearInterval(interval);

  }, []);
  
   const login = async (user:LoginData)=> {
    try {
      setIsLoading(true);
    await authApi.post('/login', user, {withCredentials:true});
     toast.success("signed in successfully!");
    return true;
    } catch(error) {
     toast.error("sign in failed");
      return false;
    } finally {
      setIsLoading(false);
    }
  }

  const signup = async (user:SignupData)=> {
    try {
      setIsLoading(true);
      await authApi.post('/signup', user);
      toast.success("signed up successfully!");
    return true;
    } catch(error) {
      toast.error("sign up failed");
      return false;
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <AuthContext.Provider value={{ login, signup, checkAuth, user}}>
      {children}
    </AuthContext.Provider>
  );
};

const useAuth = ()=> {
  const context = useContext(AuthContext);
    if (!context) {
      throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
  };

export {AuthProvider, useAuth};
