import { createContext, ReactNode, FC, useContext, useState, SetStateAction, Dispatch } from "react";
import axios from "axios";
import { LoginData } from "../types/LoginData";
import { SignupData } from "../types/SignupData";

interface AuthContextType {
  login: (user: LoginData) => Promise<void>;
  signup: (user:SignupData)=> Promise<void>;
  isUserAuthenticated:boolean,
  setIsUserAuthenticated:Dispatch<SetStateAction<boolean>>
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
     const {data} = await api.post('/login', user, {withCredentials:true});
     alert(data);
    } catch(error) {
      alert(error);
    }
  }

  const signup = async (user:SignupData)=> {
    try {
     const {data} = await api.post('/signup', user);
     
     alert(data);
    } catch(error) {
      alert(error);
    }
  }

  return (
    <AuthContext.Provider value={{ login, signup, isUserAuthenticated, setIsUserAuthenticated }}>
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
