export type User = {
    id:number,
    username:string,
    email:string,
    roomUsers:RoomUser[],
    roomUserRequests: RoomUserRequest[]
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

export type RoomUserRequest = {
    id: number,
    status: string
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

export type JoinRoom = {
    roomName: string,
    userId: number
}

export type HandleRoomUserRequest = {
    roomUserRequestId: number,
    status: string
}

export type CompileCode  = {
    language: string, 
    code: string;
}

export type ProgLang = {
    name:string, 
    img:string, 
    compName:string
}

export type CompileResult = {
    output: string,
    statusCode: number,
}