import { FC } from 'react'
import './App.css'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import { router } from './routes/Routes.tsx'
import { AuthProvider } from './contexts/AuthContext.tsx'
import { ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const App:FC = ()=> {
  return (
    <>
    <AuthProvider>
    <RouterProvider router={router}>
    </RouterProvider>
    </AuthProvider>
    <ToastContainer position='bottom-right' limit={2} autoClose={1500}/>
   </>
  )
}

export default App
