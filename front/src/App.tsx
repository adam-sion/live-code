import { FC } from 'react'
import './App.css'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import { router } from './routes/Routes.tsx'
import { AuthProvider } from './contexts/AuthContext.tsx'

const App:FC = ()=> {
  return (
    <AuthProvider>
    <RouterProvider router={router}>
    </RouterProvider>
    </AuthProvider>
   
  )
}

export default App
