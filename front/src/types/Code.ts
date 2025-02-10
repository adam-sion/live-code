export type User = {
    id:number,
    username:string,
    email:string,
    roomUsers:RoomUser[]
}

export type RoomUser = {
    id:RoomUserId
    role:string,
    room:Room,
    active:boolean
}

export type RoomUserId = {
    roomId:number,
    userId:number
}


export type Room =  {
    name:string,
    roomId:number,
    roomUsers:RoomUser[]
}

export type CodeMessage  = {
    roomName: string, 
    language: string, 
    code: string;
}