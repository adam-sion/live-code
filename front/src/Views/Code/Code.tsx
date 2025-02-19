import { Editor } from "@monaco-editor/react";
import ReplyIcon from '@mui/icons-material/Reply';
import debounce from "lodash-es/debounce";
import { FC, useCallback, useEffect, useState } from "react";
import { Accordion, AccordionDetails, AccordionSummary, AppBar, Box, Button, Divider, Drawer, Grid, Grid2, IconButton, ListItem, ListItemButton, ListItemText, MenuItem, Paper, Select, SelectChangeEvent, Stack, styled, Switch, SwitchProps, Tab, Tabs, TextareaAutosize, Toolbar, Typography, useRadioGroup } from "@mui/material";
import { progLangs } from "./data";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Bolt, Close, ConnectWithoutContact, Home, Login } from "@mui/icons-material";
import { FixedSizeList, ListChildComponentProps} from "react-window";
import timePic from "../../assets/time.png";
import TaskAltIcon from '@mui/icons-material/TaskAlt';
import CloseIcon from '@mui/icons-material/Close';
import { FormData, RoomForm } from "../../components/RoomForm/RoomForm";
import darklogo from "../../assets/newdarklogo.png";
import { Link } from "react-router-dom";
import { Room, RoomUser, User } from "../../types/Code";
import { useAuth } from "../../contexts/AuthContext";
import { useCreateRoom } from "../../api/hooks/useCreateRoom";
import ConnectWithoutContactIcon from '@mui/icons-material/ConnectWithoutContact';
import WebSocketService from "../../webscoket/WebSocketService";
import { toast } from "react-toastify";
import { useGetCode } from "../../api/hooks/useGetCode";
import { useSetRoomUserActive } from "../../api/hooks/useSetRoomUserActive";
import backg from "../../assets/backg.webp"
import MeetingRoomIcon from '@mui/icons-material/MeetingRoom';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import DriveFileRenameOutlineIcon from '@mui/icons-material/DriveFileRenameOutline';
import ContactEmergencyIcon from '@mui/icons-material/ContactEmergency';

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
  <ListItem
    style={{
      backgroundColor: "rgba(205, 196, 196, 0.1)",
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
      
    
  </ListItem>
</ListItem>

  );
}

export const Code: FC = () => {
  const [code, setCode] = useState("// Write your code here...");
  const [progLang, setProgLang] = useState<{ name: string; img: string } | undefined>(progLangs[0]);
 const {user, setUser, getUser} = useAuth();
const {addRoom} = useCreateRoom();
const {setActive} = useSetRoomUserActive();
const {getCode} = useGetCode();

const handleJoinRoom = async (room:FormData)=> {
  
}

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
   <ListItem
     style={{
       backgroundColor: "rgba(131, 114, 114, 0.1)",
       padding: "0 8px", // Adjust as needed for consistency
     }}
   >
    <ListItemText primary={user?.roomUsers[index]?.room?.name}/>
    <IOSSwitch name={`${index}`} checked={user?.roomUsers[index]?.active} onChange={() => handleToggleRoomActive(index)}></IOSSwitch>
    </ListItem>
 </ListItem>
  )
}
const handleToggleRoomActive = async (index: number) => {
  if (!user) return;

  const currRoomUser = user.roomUsers.find((_, i) => i === index);
  if (!currRoomUser) return;

  if (currRoomUser.active && selectedRoom === currRoomUser.room.name) {
    setSelectedRoom(undefined);
  }
  setUser((prevUser) => ({
    ...prevUser!!,
    roomUsers: prevUser!!.roomUsers.map((roomUser, i) =>
      i === index ? { ...roomUser, active: !roomUser.active } : roomUser
    ),
  }));
  await setActive(!currRoomUser.active, currRoomUser.id);

  
};

const handleCreateRoom = async (room:FormData)=> {
  const newRoom = await addRoom(room.roomName);
  if (newRoom) {
    await getUser();
  }
   
}

const [selectedRoom, setSelectedRoom] = useState<string|undefined>(undefined);
const [currentTab, setCurrentTab] = useState<number>(0);

  const handleEditorChange = useCallback(
    debounce((value: string | undefined) => {
      setCode(value || "");

      WebSocketService.sendMessage("/app/sendCodeLineOperation", { roomName: selectedRoom!! , language: progLang?.name!!, code: value || "" });
    }, 400),
    [selectedRoom, progLang]
  );
  const handleLangChange = async (event: SelectChangeEvent) => {
    const prog =progLangs.find((currProgLang) => currProgLang.name === event.target.value as string); 
    setProgLang(prog);
    setCode(await getCode(selectedRoom!!, prog?.name!!))
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





  const handleChange = async (event: React.SyntheticEvent, newValue: string) => {
    setSelectedRoom(newValue);
    setCode(await getCode(newValue, progLang?.name!!))
  };
  

  useEffect(() => {
    // Function to update the time and date
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
  
    // Start interval to update time every second
    const intervalId = setInterval(updateDateTime, 1000);
    updateDateTime(); // Call immediately to avoid 1-second delay
  
    // Connect to WebSocket
    WebSocketService.connect(() => {
      console.log("Connected to WebSocket!");
  
      // Subscribe to the selected room and language if selected
      if (selectedRoom && progLang?.name) {
        console.log("Subscribing to room and language:", selectedRoom, progLang.name);
        WebSocketService.subscribeToTopic(`/topic/roomCode/${selectedRoom}/${progLang.name}`, (message) => {
          console.log("Received message:", message);
          setCode(message.code);
        });
      }
  
      // Assuming `rooms` is the list of rooms the user belongs to, and `role` is the user's role in that room
      const roomsUserIsAdminOf = user?.roomUsers.filter(roomUser => roomUser.role === "ADMIN"); // Filter rooms where user is an ADMIN
  
      if (roomsUserIsAdminOf) {
      roomsUserIsAdminOf.forEach((roomUser) => {
        console.log("Subscribing to joinRoom for:", roomUser.room.name); // Assuming `roomName` is the room identifier
        WebSocketService.subscribeToTopic(`/topic/${roomUser.room.name}/joinRoom`, (message) => {
          console.log("Join room message:", message);
          // Handle the message (e.g., update UI or handle join event)
        });
      });
    }
    });
  
    return () => {
      // Cleanup interval and WebSocket subscriptions
      clearInterval(intervalId);
  
      // Unsubscribe from the selected room and language when component unmounts or when room changes
      if (selectedRoom && progLang?.name) {
        WebSocketService.unsubscribeFromTopic(`/topic/roomCode/${selectedRoom}/${progLang.name}`);
      }
  
      // Unsubscribe from all joinRoom topics for rooms the user has an ADMIN role
      const roomsUserIsAdminOf = user?.roomUsers.filter(roomUser => roomUser.role === "ADMIN");// Filter rooms where user is an ADMIN
      if (roomsUserIsAdminOf) {
      roomsUserIsAdminOf.forEach((roomUser) => {
        WebSocketService.unsubscribeFromTopic(`/topic/${roomUser.room.name}/joinRoom`);
      });
    }
    };
  }, [selectedRoom, progLang?.name, user?.roomUsers]); // Re-run effect when selectedRoom, progLang.name, or rooms change
  
  



  return (
    <Box
      sx={{
        height:'100%',
      
     minHeight:'100vh',
        display: "flex",
        flexDirection: "column",
        backgroundColor:"rgba(0,0,0,0.1)",
      }}
    >
  
      <AppBar
        sx={{
          width:"100%",
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
  <Box sx={{paddingTop:'15px', textAlign:'center'}}>
  <ConnectWithoutContactIcon sx={{fontSize:'30px'}}/>
  <Tabs
  indicatorColor="secondary"
  textColor="inherit"
  orientation="vertical"
  variant="scrollable"
  value={selectedRoom || false}
  onChange={handleChange}
  aria-label="Vertical tabs example"
  sx={{ borderRight: 1, borderColor: 'divider', alignItems:'center' }}
>
  {user?.roomUsers.map((roomUser: RoomUser) =>
    roomUser.active ? (
      <Tab key={roomUser.room.name} value={roomUser.room.name} label={roomUser.room.name} />
    ) : null
  )}
</Tabs>

</Box>
  <Box
    sx={{
      paddingTop:2,
      flex: 1, 
      minHeight:'100%'// Take equal width
    }}
  >

  {
    selectedRoom !== undefined ? <Editor
    height={'100%'}
       // Fill parent container height
       // Fill parent container width
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
    /> :

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
  No room was chosen yet
  
</Box>
  }
    
   
  </Box>
  <Box
    sx={{
   
      width:{lg:'40%', sm:'100%'},
      display: "flex", // Optional: for content alignment inside this box
      flexDirection:"column",
      alignItems:'center',
     justifyContent:'center',
      gap:3
    }}
  >
<Paper
  sx={{
    borderRadius: '12px',
    padding: 4,
    gap: 10,
    width: '70%',
    backgroundImage: `url(${backg})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
  }}
>
    {
      currentTab === 0 ? 
(
      <Grid container spacing={4} columns={2}>
        <Grid item xs={2}>
          <ListItemButton
          onClick={()=> setCurrentTab(1)}
            sx={{
              height: 100,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              backgroundColor: "#f0f0f0",
              transition: "background-color 0.3s ease, transform 0.2s ease", // Smooth transition
    "&:hover": {
      backgroundColor: "#e0e0e0", // Lighter background on hover
      transform: "scale(1.05)", // Slight scale-up effect
    },
    "&:active": {
      transform: "scale(0.95)", // Press-down effect
    },
            }}
          >
            <Box
            sx={{   
              textAlign:'center',
                fontSize: '30px',
               
                color: 'black',
              
          
                fontFamily: 'Gill Sans, Verdana',
            
               
            }}
        >
        
         
           My Rooms
           <IconButton>
          <MeetingRoomIcon sx={{color:'black', marginLeft:'10px'}}/>
          </IconButton>
          
        </Box>
          </ListItemButton>
        </Grid>

        <Grid item xs={2}>
          <ListItemButton
          onClick={()=> setCurrentTab(2)}
            sx={{
              height: 100,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              backgroundColor: "#f0f0f0",
              transition: "background-color 0.3s ease, transform 0.2s ease", // Smooth transition
              "&:hover": {
                backgroundColor: "#e0e0e0", // Lighter background on hover
                transform: "scale(1.05)", // Slight scale-up effect
              },
              "&:active": {
                transform: "scale(0.95)", // Press-down effect
              },
            }}
          >
            <Box
            sx={{   
              textAlign:'center',
                    fontSize: '30px',
               
                color: 'black',
              
          
                fontFamily: 'Gill Sans, Verdana',
            
               
            }}
        >
           Date & Time
           <IconButton>
          <CalendarMonthIcon sx={{color:'black', marginLeft:'10px'}}/>
          </IconButton>
          
          
        </Box>
          </ListItemButton>
        </Grid>

        <Grid item xs={2}>
          <ListItemButton
          onClick={()=> setCurrentTab(3)}
            sx={{
              height: 100,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              backgroundColor: "#f0f0f0",
              transition: "background-color 0.3s ease, transform 0.2s ease", // Smooth transition
              "&:hover": {
                backgroundColor: "#e0e0e0", // Lighter background on hover
                transform: "scale(1.05)", // Slight scale-up effect
              },
              "&:active": {
                transform: "scale(0.95)", // Press-down effect
              },
            }}
          >
            <Box
            sx={{   
              textAlign:'center',
              fontSize: '30px',
               
                color: 'black',
              
          
                fontFamily: 'Gill Sans, Verdana',
            
               
            }}
        >
         Join/Create room
         <IconButton>
          <DriveFileRenameOutlineIcon sx={{color:'black', marginLeft:'10px'}}/>
          </IconButton>
          
          
        </Box>
          </ListItemButton>
        </Grid>

        <Grid item xs={2}>
          <ListItemButton
          onClick={()=> setCurrentTab(4)}
          
            sx={{
              height: 100,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              backgroundColor: "#f0f0f0",
              transition: "background-color 0.3s ease, transform 0.2s ease", // Smooth transition
              "&:hover": {
                backgroundColor: "#e0e0e0", // Lighter background on hover
                transform: "scale(1.05)", // Slight scale-up effect
              },
              "&:active": {
                transform: "scale(0.95)", // Press-down effect
              },
            }}
          >
            <Box
            sx={{   
              textAlign:'center',
              fontSize: '30px',
               
                color: 'black',
              
          
                fontFamily: 'Gill Sans, Verdana',
            
               
            }}
        >
           User requests

           <IconButton>
          <ContactEmergencyIcon sx={{color:'black', marginLeft:'10px'}}/>
          </IconButton>

          
        </Box>
          </ListItemButton>
        </Grid>
    
    </Grid>)
     : (
    <Box>
      <Box>
        <IconButton onClick={()=> setCurrentTab(0)}>
<ReplyIcon sx={{color:'black', fontSize:'40px'}}/>
</IconButton>
</Box>
    {currentTab === 1 ?
    (
      <div style={{display:'flex', justifyContent:'center'}}>
      {/* //my rooms */}
<FixedSizeList

style={{ overflowY: (user?.roomUsers.length ? user.roomUsers.length :46) * 46 > 300 ? "auto" : "hidden"}}
       height={user? user.roomUsers.length*46: 0}
       width={360}
       itemSize={(46)}
       itemCount={(user?.roomUsers.length ? user.roomUsers.length :0)}
       overscanCount={5}
     >
       {renderRow}
     </FixedSizeList>
     </div>
    ) :
    currentTab === 2 ? 
    (
      <Box sx={{display:'flex', justifyContent:'center'}}>
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
    </Box>
    ) : currentTab === 3 ?
    (
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
          
  <RoomForm method="Join" onSubmit={handleJoinRoom}/>
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
          <RoomForm method="Create" onSubmit={handleCreateRoom}/>
          </AccordionDetails>
        </Accordion>
        </Box>
    ) :
  (
    <Box sx={{display:'flex', justifyContent:'center'}}>

<FixedSizeList
 style={{  overflowY: 6 * 46 > 300 ? "auto" : "hidden"}}
        height={6*46}
        width={360}
        itemSize={46}
        itemCount={6}
        overscanCount={5}
      >
        {requestRow}
      </FixedSizeList>

    </Box>
  )
}
  </Box>
  )  }
    </Paper>

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