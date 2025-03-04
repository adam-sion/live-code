import axios from "axios";


export const useGetCode =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/roomCode`,
        withCredentials: true,
      });
      

const getCode = async (roomName:string, language:string) => {
    try {
    const {data} =  await api.get(`/${roomName}/${language}/code`);
    
    return data
    } catch(error) {
        return "";
    }
}

return {getCode};
}