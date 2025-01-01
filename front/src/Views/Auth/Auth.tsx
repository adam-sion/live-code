import { FC } from "react";
import { useState } from 'react';
import {Box, Grid} from "@mui/material";
import "./Auth.css";
import { Login } from "../../components/Login/Login";
import { Signup } from "../../components/Signup/Signup";
import darklogo from "../../assets/newdarklogo.png"
import cppimg from "../../assets/cppImg.png"
import javaimg from "../../assets/JavaImg.png"
import pyimg from "../../assets/PythonImg.png"
export const Auth: FC = () => {
    const [isSignup, setIsSignup] = useState(false);

  return (
    <div className="container">
        <Box sx={{ width:{sm:'60vw',xs:'90vw', xl:'42vw'}}}>
      <div className={`glowing-box ${isSignup? 'second-background' : 'first-background'}`}>
        <div className='box left-box'>
          {
            
             isSignup ? <Signup setIsSignup={setIsSignup}></Signup> : <Login setIsSignup={setIsSignup}/>
          }
        </div>
        <div className="box right-box">
    <Box sx={{marginBottom:{xs:'25%',sm:'20%',md:'20%', xl:'15%'},marginTop:{xs:'10%',sm:'15%', xl:'5%'}, display:'flex', justifyContent:'center'}}><img height={'100%'} width={'60%'} src={darklogo}></img></Box>
    <Grid sx={{ display: 'flex', justifyContent: 'end', height:'40%' }} container spacing={{ xs: 2, md: 1 }} columns={{ xs: 4, sm: 8, md: 12 }}>
  <Grid sx={{display:'flex', justifyContent:'center'}} item xs={2} sm={4} md={6}>
    <Box  className={`floating first-background`}><img height={'80%'} width={'80%'} src={javaimg} alt="" /></Box>
  </Grid>

  <Grid sx={{display:'flex', justifyContent:'center'}} item xs={2} sm={4} md={6}>
    <Box className={`floating first-background`}><img height={'80%'} width={'80%'} src={pyimg} alt="" /></Box>
  </Grid>

  <Grid sx={{display:'flex', justifyContent:'center'}} item xs={4} sm={8} md={12}> 
    <Box className={`floating first-background`}><img height={'80%'} width={'80%'} src={cppimg} alt="" /></Box>
  </Grid>
</Grid>

        </div>
      </div>
      </Box>
    </div>
  );
};
