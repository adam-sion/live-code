import { Navigate, Outlet } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { useEffect, useState } from "react";
import { useLoading } from "../contexts/loadingContext";

const ProtectedRoute = () => {
  const {checkAuth } = useAuth();
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);
  const {loadingSpinner} = useLoading();

  useEffect(() => {
    (async () => {
      const isAuth = await checkAuth();
      setIsAuthenticated(isAuth!!);
    })();
  }, []);

  if (isAuthenticated === null) {
    return loadingSpinner;
  }

  return isAuthenticated == true ? <Outlet /> : <Navigate to="/auth" replace />;
};

export default ProtectedRoute;
