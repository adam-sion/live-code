import { createBrowserRouter } from "react-router-dom";
import { Login } from "../Views/Login/Login";
import { Home } from "../Views/Home/Home";

export const router = createBrowserRouter([
{path:"/", element:<Home/>},
{path:"login", element:<Login/>},
{path:"signup", element:<Login/>},
{path:"*", element:<Home/>}
])