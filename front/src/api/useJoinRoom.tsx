import axios from "axios";
import { HandleRoomUserRequest, JoinRoom } from "../types/Code";
import { toast } from "react-toastify";

export const useJoinRoomRequest =  ()=> {
    const api = axios.create({
        baseURL: `${import.meta.env.VITE_SERVER_URL}/roomUserRequest`,
        withCredentials: true,
      });

const getAll = async (adminId: number) => {
        try {
        const {data} =  await api.get(`/${adminId}`);
        console.log(data);
        return data;
        } catch(error) {
            console.log(error);
            return [];
        }
    }

const createRoomUserRequest = async (joinRoom: JoinRoom) => {
    try {
    const {data} =  await api.post("/create", joinRoom);
    toast.success("Join room request sent successfully!");
    return data;
    } catch(error) {
        console.log(error);
        toast.error("join room request failed");
    }
}

const handleRoomUserRequest = async (handleRoomUserRequest: HandleRoomUserRequest) => {
    try {
    const {data} =  await api.post("/handle", handleRoomUserRequest);
    toast.success("handled request successfully!");
    return data;
    } catch(error) {
        console.log(error);
        toast.error("roomUserRequest handling failed");
    }
}

return {createRoomUserRequest, handleRoomUserRequest, getAll};
}