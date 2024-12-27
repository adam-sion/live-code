import { FC } from 'react'
import './App.css'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import { router } from './Routes/Routes.tsx'


const App:FC = ()=> {
  return (
    <RouterProvider router={router}></RouterProvider>
  )
}

export default App
