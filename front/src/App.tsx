import { FC } from 'react'
import './App.css'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import { router } from './routes/Routes.tsx'
import { AuthProvider } from './contexts/AuthContext.tsx'
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer} from 'react-toastify';

const App:FC = ()=> {
  return (

    <AuthProvider>
    <RouterProvider router={router}>
    </RouterProvider>
    <ToastContainer position='bottom-right' limit={2} autoClose={1500}/>
    </AuthProvider>
    
  )
}

export default App
