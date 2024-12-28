import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";


const ProtectedRoute = () => {
  const { isUserAuthenticated } = useAuth();

  console.log(`auth is ${isUserAuthenticated}`);
  return isUserAuthenticated ? <Outlet /> : <Navigate to="/auth" replace />;
};

export default ProtectedRoute;
