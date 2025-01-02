import { createContext, ReactNode, FC, useContext, useState, useEffect, Dispatch, SetStateAction} from "react";
import axios from "axios";
import { LoginData } from "../types/LoginData";
import { SignupData } from "../types/SignupData";
import { toast } from "react-toastify";
import { useLoading } from "./loadingContext";
interface AuthContextType {
  login: (user: LoginData) => Promise<boolean|undefined>;
  signup: (user:SignupData)=> Promise<boolean>;
  checkAuth: ()=> Promise<boolean|undefined>
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

const AuthProvider: FC<AuthProviderProps> = ({ children }) => {
  const api = axios.create({ baseURL: `${import.meta.env.VITE_SERVER_URL}/auth` });
   const {setIsLoading} = useLoading();
 
  const checkAuth = async () => {
    try {
     
      const response = await api.post('/checkLoggedIn',{}, { withCredentials: true });
      if (response.status === 200) {
       return true;
      }
    } catch (error) {
      console.error('Error checking authentication', error);
      return false;
    }
  };


   useEffect( () => {
   checkAuth();
    const responseInterceptor = api.interceptors.response.use(
      (response) => response,
      async (error) => {
        const originalRequest = error.config;

        if (
          error.response?.status === 401 &&
          !originalRequest._retry &&
          error.response?.data?.message === "Token expired"
        ) {
          originalRequest._retry = true;

          try {
          
            await api.post("/refresh-token");
        
            return api(originalRequest);
          } catch (refreshError) {
            toast.error("Session expired. Please log in again.");
            return Promise.reject(refreshError);
          }
        }

        return Promise.reject(error);
      }
    );

    return () => {

      api.interceptors.response.eject(responseInterceptor);
    };
  }, []);
  
   const login = async (user:LoginData)=> {
    try {
      setIsLoading(true);
    await api.post('/login', user, {withCredentials:true});
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
      await api.post('/signup', user);
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
    <AuthContext.Provider value={{ login, signup, checkAuth}}>
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
