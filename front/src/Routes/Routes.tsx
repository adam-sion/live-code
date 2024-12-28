import { createBrowserRouter } from "react-router-dom";
import { Auth } from "../Views/Auth/Auth";
import { Home } from "../Views/Home/Home";
import ProtectedRoute from "./ProtectedRoute";


export const router = createBrowserRouter([
    {path:"auth", element:<Auth/>},
    {
        path:"/", element:<ProtectedRoute/>,
    children:[
        {path:"/", element:<Home/>},
        {path:"*", element:<Home/>}]
    }
])