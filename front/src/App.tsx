import { FC } from 'react'
import './App.css'
import './index.css'
import {RouterProvider } from 'react-router-dom';
import { router } from './routes/Routes.tsx';
import { AuthProvider } from './contexts/AuthContext.tsx';
import { ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { LoadingProvider } from './contexts/loadingContext.tsx';

const App:FC = ()=> {

  return (
    <LoadingProvider>
    <AuthProvider>
    <RouterProvider router={router}/>
    <ToastContainer position='bottom-right' limit={2} autoClose={1500}/>
    </AuthProvider>
    </LoadingProvider>
    
  )
}

export default App
