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


type Room =  {
    name:string,
    roomUsers:RoomUser[]
}