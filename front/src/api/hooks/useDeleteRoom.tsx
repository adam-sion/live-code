import axios from "axios";
import { toast } from "react-toastify";


export const useDeleteRoom =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/rooms`,
        withCredentials: true,
      });
      

const deleteRoom = async (name:string) => {
    try {
    const {data} =  await api.delete(`/${name}`);
    toast.info("room deleted successfully!");
    return data;
    } catch(error) {
        console.log(error);
        toast.error("delete room failed");
    }
}

return {deleteRoom};
}