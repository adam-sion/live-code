import { createContext, ReactNode, FC, useContext, useState} from "react";
import axios from "axios";
import { LoginData } from "../types/LoginData";
import { SignupData } from "../types/SignupData";
import Swal from "sweetalert2"
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
     Swal.fire({
      title: "Success",
      text: "signed in successfully!",
      icon: "success"
    });
    return true;
    } catch(error) {
      Swal.fire({
        title: "Failed",
        text: "sign in falied",
        icon: "error"
      });
      return false;
    }
  }

  const signup = async (user:SignupData)=> {
    try {
      await api.post('/signup', user);
     Swal.fire({
      title: "Success",
      text: "signed up successfully",
      icon: "success"
    });
    return true;
    } catch(error) {
      Swal.fire({
        title: "Failed",
        text: "sign up falied",
        icon: "error"
      });
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
