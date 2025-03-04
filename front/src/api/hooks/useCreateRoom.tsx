import axios from "axios";
import { toast } from "react-toastify";


export const useCreateRoom =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/rooms`,
        withCredentials: true,
      });
      

const addRoom = async (name:string) => {
    try {
    const {data} =  await api.post("",{roomName:name});
    toast.success("room created successfully!");
    return data;
    } catch(error) {
        console.log(error);
        toast.error("create room failed");
    }
}

return {addRoom};
}