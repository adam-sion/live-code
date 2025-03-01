import { yupResolver } from "@hookform/resolvers/yup";
import { Box, Button, IconButton, InputAdornment, Stack, TextField } from "@mui/material";
import { FC } from "react";
import { useForm } from "react-hook-form";
import * as Yup from "yup";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

const RoomSchema = Yup.object().shape({
    roomName: Yup.string()
      .required("Room name is required")
      .max(100, "reached chars limit")
  });

 export type FormData = {
    roomName:string
  }

  type props = {
    method:string,
    onSubmit:(data:FormData)=> void
  }

export const RoomForm:FC<props> = ({method, onSubmit})=> {
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
      } = useForm<FormData>({
        resolver: yupResolver(RoomSchema),
      });
     
   return ( <Box
    component={"form"}
    onSubmit={handleSubmit((data) => {
      onSubmit(data); 
      reset();       
    })}
    sx={{
      display: "flex",
      justifyContent: "center",
     
    }}

  >
    
    <Stack
      direction="column"
      sx={{
        width: { xs: "70%", sm: "70%", md: "50%" },
        justifyContent: "start",
        alignItems: "center"
      }}
      >
    
      <TextField
        label="room name"
        // error={!!errors.username}
        // helperText={errors.username ? errors.username.message : ""}
        type="text"
        variant="standard"
        sx={{
          width:'100%',
        }}
        {...register("roomName")}
        InputProps={{
          endAdornment: errors.roomName ? (
            <ErrorOutlineIcon sx={{ color: "red" }} />
          ) : (
            <InputAdornment position="end">
              <IconButton edge="end" aria-label="username tag">
             
              </IconButton>
            </InputAdornment>
          ),
        }}
      />
       <Box sx={{ marginTop: 3, width: "100%" }}>
       <Button
          type="submit"
          variant="contained"
          sx={{
            borderRadius: 20,
            width: "100%",
            fontWeight: "bold",
            background: "linear-gradient(55deg, #004E95, #add8e6)",
            color: "white",
            boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.3)",
            fontFamily: '"Comic Sans MS", "Comic Sans", cursive',
          }}
        >
          {method}
        </Button>
        </Box>
</Stack>

</Box>)
}