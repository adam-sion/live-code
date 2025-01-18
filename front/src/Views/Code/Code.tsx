import { Editor } from "@monaco-editor/react";
import { FC, useEffect, useState } from "react";
import { Accordion, AccordionDetails, AccordionSummary, AppBar, Box, Button, Divider, Drawer, IconButton, ListItem, ListItemButton, ListItemText, MenuItem, Select, SelectChangeEvent, Stack, styled, Switch, SwitchProps, TextareaAutosize, Toolbar, Typography } from "@mui/material";
import { progLangs } from "./data";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Bolt, Close, Home, Login } from "@mui/icons-material";
import { FixedSizeList, ListChildComponentProps} from "react-window";
import timePic from "../../assets/time.png";
import TaskAltIcon from '@mui/icons-material/TaskAlt';
import CloseIcon from '@mui/icons-material/Close';
import { RoomForm } from "../../components/RoomForm/RoomForm";
import darklogo from "../../assets/newdarklogo.png";
import { Link } from "react-router-dom";
import { User } from "../../types/User";
import { useAuth } from "../../contexts/AuthContext";
const IOSSwitch = styled((props: SwitchProps) => (
  <Switch focusVisibleClassName=".Mui-focusVisible" disableRipple {...props} />
))(({ theme }) => ({
  width: 42,
  height: 26,
  padding: 0,
  '& .MuiSwitch-switchBase': {
    padding: 0,
    margin: 2,
    transitionDuration: '300ms',
    '&.Mui-checked': {
      transform: 'translateX(16px)',
      color: '#fff',
      '& + .MuiSwitch-track': {
        backgroundColor: '#65C466',
        opacity: 1,
        border: 0,
        ...theme.applyStyles('dark', {
          backgroundColor: '#2ECA45',
        }),
      },
      '&.Mui-disabled + .MuiSwitch-track': {
        opacity: 0.5,
      },
    },
    '&.Mui-focusVisible .MuiSwitch-thumb': {
      color: '#33cf4d',
      border: '6px solid #fff',
    },
    '&.Mui-disabled .MuiSwitch-thumb': {
      color: theme.palette.grey[100],
      ...theme.applyStyles('dark', {
        color: theme.palette.grey[600],
      }),
    },
    '&.Mui-disabled + .MuiSwitch-track': {
      opacity: 0.7,
      ...theme.applyStyles('dark', {
        opacity: 0.3,
      }),
    },
  },
  '& .MuiSwitch-thumb': {
    boxSizing: 'border-box',
    width: 22,
    height: 22,
  },
  '& .MuiSwitch-track': {
    borderRadius: 26 / 2,
    backgroundColor: '#E9E9EA',
    opacity: 1,
    transition: theme.transitions.create(['background-color'], {
      duration: 500,
    }),
    ...theme.applyStyles('dark', {
      backgroundColor: '#39393D',
    }),
  },
}));

const renderRow = (props: ListChildComponentProps)=> {
  const { index, style } = props;
  return (
    <ListItem
   style={{
     ...style,
     padding: 0, // Remove additional padding
     margin: 0, // Remove extra margin
   }}
   key={index}
   component="div"
   disablePadding
 >
   <ListItemButton
     style={{
       backgroundColor: "rgba(131, 114, 114, 0.1)",
       padding: "0 8px", // Adjust as needed for consistency
     }}
   >
    <ListItemText primary={`Item ${index + 1}`}/>
    <IOSSwitch></IOSSwitch>
    </ListItemButton>
 </ListItem>
  )
}

const requestRow = (props: ListChildComponentProps)=> {
  const { index, style } = props;

  return (
   <ListItem
  style={{
    ...style,
    padding: 0, // Remove additional padding
    margin: 0, // Remove extra margin
  }}
  key={index}
  component="div"
  disablePadding
>
  <ListItemButton
    style={{
      backgroundColor: "rgba(131, 114, 114, 0.1)",
      padding: "0 8px", // Adjust as needed for consistency
    }}
  >
    <ListItemText primary={`Item ${index + 1}`} />
   
      
        <IconButton aria-label="confirm" color="success" size="small">
          <TaskAltIcon />
        </IconButton>
        <IconButton aria-label="confirm" color="error" size="small">
          <CloseIcon />
        </IconButton>
      
    
  </ListItemButton>
</ListItem>

  );
}

export const Code: FC = () => {
  const [code, setCode] = useState("// Write your code here...");
  const [progLang, setProgLang] = useState<{ name: string; img: string } | undefined>(progLangs[0]);
const [count, setCount] = useState(6);
const {user} = useAuth();



  const handleEditorChange = (value: string | undefined) => {

    setCode(value || "");
  };

  const handleLangChange = (event: SelectChangeEvent) => {
    setProgLang(progLangs.find((currProgLang) => currProgLang.name === event.target.value as string));
  };

  const [isConsoleOpen, setConsoleOpen] = useState(false);
  const [output, setOutput] = useState("");

  const handleRunClick = () => {
    setConsoleOpen(true);

    
    setTimeout(() => {
      setOutput("Compilation successful! Output:\nHello, World!");
    }, 1000); 
  };

  const handleCloseConsole = () => {
    setConsoleOpen(false);
  };
  const [time, setTime] = useState<string>("");
  const [date, setDate] = useState<string>("");


  useEffect(() => {
    const updateDateTime = () => {
      const now = new Date();
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = String(now.getMinutes()).padStart(2, "0");
      const seconds = String(now.getSeconds()).padStart(2, "0");

      const currentTime = `${hours}:${minutes}:${seconds}`;
      const currentDate = now.toISOString().split("T")[0];

      setTime(currentTime);
      setDate(currentDate);
    };

    // Update time and date every second
    const interval = setInterval(updateDateTime, 1000);

    // Cleanup interval on component unmount
    return () => clearInterval(interval);
  }, []);

  return (
    <Box
      sx={{
     
        display: "flex",
        flexDirection: "column",
        backgroundColor:"rgba(0,0,0,0.1)",
      }}
    >
      <AppBar
        sx={{
          backgroundColor: "transparent",
          boxShadow: "none",
          position: "static",
          background: "rgba(58, 47, 143, 0.8)",
          display: "flex",
          alignItems: {xs:'center',sm: "center", md: "start" },
          flexDirection: { md: "row"},
          
        }}
      >
        <Toolbar sx={{ justifyContent: "start", gap: {md:15, sm:5, xs:3}, width: '80%' }}>
          <Box>
            <Select
              labelId="demo-simple-select-label"
              sx={{
                height: "30px",
              }}
              id="demo-simple-select"
              value={progLang?.name}
              onChange={handleLangChange}
            >
              {progLangs.map((currProgLang) => (
                <MenuItem key={currProgLang.name} value={currProgLang.name}>
                  <Box sx={{ display: "flex", flexDirection: "row", alignItems: "center" }}>
            
                    <img style={{ marginLeft: "3px" }} height="20px" src={currProgLang.img} alt="" />
                  </Box>
                </MenuItem>
              ))}
            </Select>
          </Box>
          <Button onClick={handleRunClick} sx={{ padding: "2" }} color="success" variant="contained" endIcon={<Bolt />}>
            Run
          </Button>
          <img src={darklogo} alt="" />
        </Toolbar>
        <Toolbar sx={{ justifyContent:'center', gap: {md:10, sm:5, xs:3},width: '80%' }}>
        <Box
            sx={{   
              height:'100%',
              textAlign:'center',
                fontSize: '20px',
                m: 1,
                color: 'black',
                padding:1,
              boxShadow: "0 0 15px 5px rgba(154, 162, 164, 0.7)",
              borderRadius:'12px',
                fontFamily: 'Gill Sans, Verdana',
              
               
            }}
        >
           Welcome back, {user?.username}
          
        </Box>
        <Link to={"/"}>
        <IconButton aria-label="confirm">
          <Home sx={{fontSize:'30px'}}/>
        </IconButton>
        </Link>
        <Link to={"/auth"}>
        <IconButton aria-label="confirm">
          <Login sx={{fontSize:'30px'}}/>
        </IconButton>
        </Link>
        </Toolbar>
      </AppBar>
      <Box
  sx={{
    flexGrow: 1,
    display: "flex", // Enable flexbox for equal width
    flexDirection: { xs: "column", sm: "column", md: "column", lg: "row" },

    gap:'15px',
    
  }}
>
  <Box
    sx={{
      flex: 1 // Take equal width
    }}
  >
    <Editor
      
      height="99%"
       // Fill parent container height
      width="100%" // Fill parent container width
      language={progLang?.name}
      defaultValue="// Write your code here..."
      value={code}
      onChange={handleEditorChange}
      theme="vs-dark"
      options={{
        fontSize: 26,
        lineHeight: 26,
        minimap: { enabled: false },
      }}
    />
   
  </Box>
  <Box
    sx={{

      flex: 1, // Take equal width
      display: "flex", // Optional: for content alignment inside this box
      flexDirection:"column",
      gap:3,
      padding:3
    }}
  >
   <Box sx = {{ display:"flex", flexWrap:'wrap', justifyContent:'center', padding:2, paddingBottom:5, gap:10}}>
   
 <Box sx={{display:'flex', flexDirection:'column', gap:1.5}}>
 <Box
            sx={{   
              textAlign:'center',
                fontSize: '20px',
                m: 1,
                color: 'black',
                padding:1,
              boxShadow: "0 0 15px 5px rgba(154, 162, 164, 0.7)",
              borderRadius:'12px',
                fontFamily: 'Gill Sans, Verdana',
                fontWeight: 'bold',
               
            }}
        >
           My Rooms
          
        </Box>

 <FixedSizeList
 style={{  overflowY: count * 46 > 300 ? "auto" : "hidden"}}
        height={Math.min(300,count*46)}
        width={360}
        itemSize={46}
        itemCount={count}
        overscanCount={5}
      >
        {renderRow}
      </FixedSizeList>


 </Box>
  <Box sx={{display:'flex', flexDirection:'column', alignItems:'center',justifyContent:'center', gap:5}}>
   <Box
      sx={{
        height: "100px",
        width: "300px",
        boxShadow: "0 0 15px 5px rgba(154, 162, 164, 0.7)",
        borderRadius: "12px",
        position: "relative", // Required for positioning child elements
        overflow: "hidden", // Ensures content doesn't overflow rounded corners
      }}
    >
      {/* Background Image */}
      <Box
        component="img"
        src={timePic}
        alt="Time"
        sx={{
          height: "100%",
          width: "100%",
          objectFit: "cover",
          position: "absolute",
          top: 0,
          left: 0,
        }}
      />

      {/* Content Overlay */}
      <Box
            sx={{
              textAlign:'center',
      
                fontSize: '30px',
                m: 1,
                color: 'black',
                padding:1,
            
                fontFamily: 'Gill Sans, Verdana',
                fontWeight: 'bold',
                textShadow: '2px 2px 4px rgba(46, 44, 44, 0.2)',
                position: "relative", // Keeps content on top of the background
                zIndex: 1,
            }}
        >
          {time}
      
        </Box>
        <Box
            sx={{
              textAlign:'center',
      
                fontSize: '20px',
               
                color: 'black',
            
            
                fontFamily: 'Gill Sans, Verdana',
               textDecoration:'underline',
                textShadow: '2px 2px 4px rgba(46, 44, 44, 0.2)',
                position: "relative", // Keeps content on top of the background
                zIndex: 1,
            }}
        >
          {date}
      
        </Box>
        
    </Box>
    
    <Box sx={{display:'flex', flexDirection:'column', gap:2}}>
    <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <Typography component="span">Join room</Typography>
        </AccordionSummary>
        <AccordionDetails>
        
<RoomForm method="Join"/>
        </AccordionDetails>
      </Accordion>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel2-content"
          id="panel2-header"
        >
          <Typography component="span">Create room</Typography>
        </AccordionSummary>
        <AccordionDetails>
        <RoomForm method="Create"/>
        </AccordionDetails>
      </Accordion>
      </Box>

   
    </Box>
 <Box sx={{display:'flex', flexDirection:'column', gap:1.5}}>
 <Box
            sx={{
              
              textAlign:'center',
              borderRadius:'12px',
              fontSize: '20px',
                m: 1,
                color: 'black',
                padding:1,
              boxShadow: "0 0 15px 5px rgba(154, 162, 164, 0.7)",
                fontFamily: 'Gill Sans, Verdana',
                fontWeight: 'bold',
             
            }}
        >
           Users requests
        </Box>

 <FixedSizeList
 style={{  overflowY: count * 46 > 300 ? "auto" : "hidden"}}
        height={Math.min(300,count*46)}
        width={360}
        itemSize={46}
        itemCount={count}
        overscanCount={5}
      >
        {requestRow}
      </FixedSizeList>


 </Box>

   </Box>
   

</Box>
</Box>
<Drawer
     ModalProps={{
      hideBackdrop: true, // Disable the backdrop
    }}
        anchor="bottom"
        open={isConsoleOpen}
        onClose={handleCloseConsole}
        sx={{

          "& .MuiDrawer-paper": {
            overflowX:'hidden',
            height: "30vh",
            background: "rgba(58, 47, 143, 0.8)",
            color: "#fff",
            padding: 2,
          },
        }}
      >
        <Box sx={{padding:"10px",  background: "rgba(58, 47, 143, 0.8)",display: "flex", justifyContent: "space-between" }}>
          <Typography variant="h6">Compilation Console</Typography>
          <IconButton onClick={handleCloseConsole} sx={{ color: "#fff" }}>
            <Close />
          </IconButton>
        </Box>
        <TextareaAutosize
          minRows={10}
          readOnly
          value={output}
          style={{
            
            width: "100%",
            height: "calc(100% - 50px)",
            background: "rgba(58, 47, 143, 0.8)",
            color: "#fff",
            border: "none",
            padding: "10px",
            fontSize: "16px",
            fontFamily: "monospace",
            resize: "none",
          }}
        />
      </Drawer>
    </Box>
  );
};
