import { Box, Button, Typography } from "@mui/material";
import { FC } from "react";
import collab from "../../assets/collab.png"
import { Link } from "react-router-dom";
export const Home: FC = () => {
  return (
    <Box sx ={{backgroundColor:'black', height:'100vh', display:'flex', justifyContent:'center'}}>
    <Box
    sx={{
        width: '80vw',
        display: 'flex',
        flexDirection: { xs: 'column', md: 'row' },
        alignItems: 'center',
        justifyContent: 'center',
        p: { xs: 2, md: 5 },
        
    
    }}
>

<Box sx={{display:'flex', alignItems:'center'}}><img height={'80%'} width={'80%'} src={collab} alt="" /></Box>
    <Typography
        sx={{
            display: 'flex',
            flexDirection: 'column',
            textAlign: { md: 'left' },
            width:{md:'30%'}
        }}
        component="div"
    >
        <Box
            sx={{
                fontSize: { xs: '12vw', sm: '9vw', md: '3vw' },
                m: 1,
                color: 'white',
                fontFamily: 'Gill Sans, Verdana',
                fontWeight: 'bold',
                textShadow: '2px 2px 4px rgba(0,0,0,0.2)',
            }}
        >
            Real-time <br /> collaboration
        </Box>

        <Box
            sx={{
                fontSize: {sm: '4vw', md: '2vw' },
                m: 1,
                color: 'white',
                fontFamily: '"Poppins", sans-serif',
                fontWeight: 'bold',
                
            }}
        >
           
           Anywhere
        </Box>
        <Box  sx={{
                fontSize: { xs: '2vw', sm: '2vw', md: '1vw' },
                m: 1,
                color: 'white',
                fontFamily: '"Poppins", sans-serif',
           
                
            }}>
Allow your users to collaborate in any proggraming language. 
Integrate live carets and cursors to show who is typing, enjoy coding along your fellas without any headaches.
        </Box>
        <Box sx={{paddingTop:'20px'}}>
            <Link to={"/code"}>
            
        <Button
            type="submit"
            variant="contained"
            sx={{
                marginTop:'10',
              borderRadius: 20,
             
              fontWeight: "bold",
              fontSize: { xs: '1.5vw', sm: '1.7vw', md: '0.9vw' },
              background: "linear-gradient(55deg,rgb(57, 211, 139), #004E95)",
              color: "white",
              boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.3)",
              fontFamily: '"Poppins", sans-serif',
            
            }}
          >
            Get Started 🎉
          </Button>
          </Link>
          </Box>
          
    </Typography>
    </Box>
    </Box>
 )
};
