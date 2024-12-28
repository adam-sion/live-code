import { createBrowserRouter } from "react-router-dom";
import { Auth } from "../Views/Auth/Auth";
import { Home } from "../Views/Home/Home";

export const router = createBrowserRouter([
{path:"/", element:<Home/>},
{path:"auth", element:<Auth/>},
{path:"*", element:<Home/>}
])