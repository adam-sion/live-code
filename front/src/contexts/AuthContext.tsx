import { createContext } from "react";
import { User } from "../types/User";

interface AuthContextType {
    login: (user:User)=> Promise<void>,

}

const AuthContext = createContext<AuthContextType|undefined>(undefined);