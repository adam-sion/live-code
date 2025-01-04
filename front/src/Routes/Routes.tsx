import { createBrowserRouter, Outlet } from "react-router-dom";
import { Auth } from "../Views/Auth/Auth";
import ProtectedRoute from "./ProtectedRoute.tsx";
import { Box } from "@mui/material";
import { Home } from "../Views/Home/Home.tsx";
import { Code } from "../Views/Code/Code.tsx";
import { Navbar } from "../components/Navbar/Navbar.tsx";

const Layout = () => {
  return (
    <>
      <Navbar /> 
  
        <Outlet />
    
    </>
  );
};

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      { path: "", element: <Home /> }, 
      { path: "auth", element: <Auth /> }, 
      {
        path: "/", 
        element: <ProtectedRoute />,
        children: [
          { path: "code", element: <Code /> }, 
        ],
      },
      { path: "*", element: <Box>Page not found</Box> }, 
    ],
  },
]);
