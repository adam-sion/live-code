import axios from "axios";
import { Room, RoomUser } from "../../types/Code";
import { toast } from "react-toastify";


export const useSetRoomUserActive =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/roomUser/active`,
        withCredentials: true,
      });
      

const setActive = async (active:boolean, roomUser:RoomUser) => {
    try {
    await api.patch(`/${active}`, roomUser);
    } catch(error) {
        console.error(error);
    }
}

return {setActive};
}