import { AppBar, Box, Toolbar, Typography } from "@mui/material";
import { FC } from "react";
import darklogo from "../../assets/newdarklogo.png";
import { Link } from "react-router-dom";
import "./Navbar.css"
export const Navbar: FC = () => {
  return (
    <AppBar position="fixed" sx={{ height: 64, backgroundColor: "transparent",boxShadow: "none" }}>
      <Toolbar sx={{ justifyContent: "center" }}>
        <Box sx={{ width: {lg:'33%', md:'50%'},borderRadius: '30px', background:'rgba(255, 255, 255, 0.8)', marginTop:'50px', display: 'flex', justifyContent:'space-between', alignItems:'center', padding:2}}>
          <Box
            component="img"
            src={darklogo}
            alt="Dark Logo"
            sx={{
                
              height: '40px',
              width: "150px",
            }}
          />
            <Box sx={{display:'flex', gap:2}}>
            <Link to={"/"} className="nav-link">
          <Typography
            variant="h5"
            sx={{
              padding:'5px',
              fontWeight: "bold",
              color: "black",
              fontFamily: '"Comic Sans MS", "Comic Sans", cursive',
              overflow: "hidden",
              "&:hover": {
                color: "black",
              },
            }}
          >
            Home
          </Typography>
          </Link>

          <Link to={"/code"} className="nav-link">
          <Typography
            variant="h5"
            sx={{
              padding:'5px',
              fontWeight: "bold",
              color: "black",
              fontFamily: '"Comic Sans MS", "Comic Sans", cursive',
              overflow: "hidden",
              "&:hover": {
                color: "black",
              },
            }}
          >
            Editor
          </Typography>
          </Link>

          <Link to={"/auth"} className="nav-link">
          <Typography
            variant="h5"
            sx={{
              padding:'5px',
              fontWeight: "bold",
              color: "black",
              fontFamily: '"Comic Sans MS", "Comic Sans", cursive',
              overflow: "hidden",
              "&:hover": {
                color: "black",
              },
            }}
          >
            Login
          </Typography>
          </Link>
        </Box>
        </Box>
       
      </Toolbar>
    </AppBar>
  );
};
