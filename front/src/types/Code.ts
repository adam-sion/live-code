export type User = {
    id:number,
    username:string,
    email:string,
    roomUsers:RoomUser[]
}

export type RoomUser = {
    role:string,
    room:Room,
    active:boolean
}


export type Room =  {
    name:string,
    roomUsers:RoomUser[]
}

export type CodeMessage  = {
    roomId: number, 
    language: string, 
    code: string;
}