import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";


const ProtectedRoute = () => {
  const { isUserAuthenticated } = useAuth();

  return isUserAuthenticated ? <Outlet /> : <Navigate to="/auth" replace />;
};

export default ProtectedRoute;
