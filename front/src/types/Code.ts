export type User = {
    id:number,
    username:string,
    email:string,
    roomUsers:RoomUser[]
}

export type RoomUser = {
    role:string,
    room:Room
}

export type RoomUserDTO = {
    role:string,
    isActive:boolean,
    username:string
}

type Room =  {
    name:string,
    roomUsers:RoomUserDTO[]
}