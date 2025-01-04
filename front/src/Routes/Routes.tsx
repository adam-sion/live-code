import { createBrowserRouter, Outlet, useLocation } from "react-router-dom";
import { Auth } from "../Views/Auth/Auth";
import ProtectedRoute from "./ProtectedRoute.tsx";
import { Box } from "@mui/material";
import { Home } from "../Views/Home/Home.tsx";
import { Code } from "../Views/Code/Code.tsx";
import { Navbar } from "../components/Navbar/Navbar.tsx";

const Layout = () => {
  const location = useLocation();
  const hideNavbarRoutes = ["/code"];

  const shouldHideNavbar = hideNavbarRoutes.some((path) =>
    location.pathname.startsWith(path)
  );

  return (
    <>
      {!shouldHideNavbar && <Navbar />}
      <main>
        <Outlet /> {/* Render nested routes here */}
      </main>
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
