import { createContext, ReactNode, FC, useContext, useState} from "react";
import axios from "axios";
import { LoginData } from "../types/LoginData";
import { SignupData } from "../types/SignupData";
import { toast } from "react-toastify";
interface AuthContextType {
  login: (user: LoginData) => Promise<boolean>;
  signup: (user:SignupData)=> Promise<boolean>;
  isUserAuthenticated:boolean
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

const AuthProvider: FC<AuthProviderProps> = ({ children }) => {
  const api = axios.create({ baseURL: `${import.meta.env.VITE_SERVER_URL}/auth` });
   const [isUserAuthenticated, setIsUserAuthenticated] = useState<boolean>(false);
   const login = async (user:LoginData)=> {
    try {
    await api.post('/login', user, {withCredentials:true});
     setIsUserAuthenticated(true);
     toast.success("signed in successfully!");
    return true;
    } catch(error) {
     toast.error("sign in failed");
      return false;
    }
  }

  const signup = async (user:SignupData)=> {
    try {
      await api.post('/signup', user);
      toast.success("signed up successfully!");
    return true;
    } catch(error) {
      toast.error("sign up failed");
      return false;
    }
  }

  return (
    <AuthContext.Provider value={{ login, signup, isUserAuthenticated}}>
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
