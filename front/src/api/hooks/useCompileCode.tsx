import axios from "axios";
import { toast } from "react-toastify";
import { CompileCode } from "../../types/Code";


export const useCompileCode =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/compile`,
        withCredentials: true,
      });
      

const compile = async (compileCode:CompileCode) => {
    try {
    const {data} =  await api.post("",compileCode);
    return data;
    } catch(error) {
        console.log(error);
        toast.error("code compilation proccess failed");
    }
}

return {compile};
}