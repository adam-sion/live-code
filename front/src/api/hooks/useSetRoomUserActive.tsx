import axios from "axios";
import { RoomUserId } from "../../types/Code";


export const useSetRoomUserActive =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/roomUser/active`,
        withCredentials: true,
      });
      

const setActive = async (active:boolean, roomUserId:RoomUserId) => {
    try {
    await api.patch(`/${active}`, roomUserId);
    } catch(error) {
        console.error(error);
    }
}

return {setActive};
}