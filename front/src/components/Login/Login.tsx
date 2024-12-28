import { Dispatch, FC, SetStateAction } from "react";
import { useState } from "react";
import {
  Box,
  Button,
  IconButton,
  InputAdornment,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";
import PersonIcon from "@mui/icons-material/Person";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";
import "./Login.css";
import { useAuth } from "../../contexts/AuthContext";
import { LoginData } from "../../types/LoginData";
import { useNavigate } from "react-router-dom";


const loginSchema = Yup.object().shape({
  username: Yup.string()
    .required("Username is required")
    .max(100, "reached chars limit"),
  password: Yup.string()
    .required("Password is required")
    .max(100, "reached chars limit"),
});

interface LoginProps {
  setIsSignup:Dispatch<SetStateAction<boolean>>
}

export const Login: FC<LoginProps> = ({setIsSignup}) => {
  const [showPassword, setShowPassword] = useState(false);
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<LoginData>({
    resolver: yupResolver(loginSchema),
  });
  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

const navigate = useNavigate();
const {login} = useAuth();

  const onSubmit = async (data: LoginData) => {
        reset(); 
        
        if (await login(data)) {
          navigate("/");
        }

    } 

  return (
    <Box
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
      sx={{
        display: "flex",
        justifyContent: "center"
      }}
      width={"100%"}
      height={"100%"}
    >
      <Stack
        direction="column"
        sx={{
          width: { xs: "70%", sm: "70%", md: "50%" },
          justifyContent: "start",
          alignItems: "center",
          gap: 2
        }}
      >
        <Box
          sx={{
            
            padding: "3px 10px",
            boxShadow: "0 0 15px 5px rgba(173, 216, 230, 0.7)",
            transition: "box-shadow 0.3s ease-in-out",
            backgroundColor: "transparent",
            borderRadius: "8px",
            textAlign: "center",
            width: { md: "80%", sm: "100%", xs: "100%" },
            "&:hover": {
              backgroundColor: "rgba(173, 216, 230, 1)",
            },
          }}
        >
          <Typography
            variant="h4"
            sx={{
              
              fontWeight: "bold",
              color: "white",
              fontFamily: '"Comic Sans MS", "Comic Sans", cursive',
              overflow: "hidden",
              "&:hover": {
                color: "black",
              },
            }}
          >
            Login
          </Typography>
        </Box>

        <TextField
          label="Username"
          error={!!errors.username}
          helperText={errors.username ? errors.username.message : ""}
          type="text"
          variant="standard"
          sx={{
            width:'100%',
            "& .MuiInputLabel-root": { color: "white" },
            "& .MuiInputBase-root": { color: "white" },
            "& .MuiInput-underline:before": { borderBottomColor: "white" },
            "& .MuiInput-underline:hover:before": {
              borderBottomColor: "white",
            },
            "& .MuiInput-underline:after": { borderBottomColor: "white" },
          }}
          {...register("username")}
          InputProps={{
            endAdornment: errors.username ? (
              <ErrorOutlineIcon sx={{ color: "red" }} />
            ) : (
              <InputAdornment position="end">
                <IconButton edge="end" aria-label="username tag">
                  <PersonIcon sx={{ color: "white" }}></PersonIcon>
                </IconButton>
              </InputAdornment>
            ),
          }}
        />

        <TextField
          error={!!errors.password}
          helperText={errors.password ? errors.password.message : ""}
          label="Password"
          type={showPassword ? "text" : "password"}
          autoComplete="current-password"
          variant="standard"
          sx={{
            width:'100%',
            "& .MuiInputLabel-root": { color: "white" },
            "& .MuiInputBase-root": { color: "white" },
            "& .MuiInput-underline:before": { borderBottomColor: "white" },
            "& .MuiInput-underline:hover:before": {
              borderBottomColor: "white",
            },
            "& .MuiInput-underline:after": { borderBottomColor: "white" },
          }}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <IconButton
                  edge="end"
                  onClick={togglePasswordVisibility}
                  aria-label="toggle password visibility"
                >
                  {errors.password ? (
                    <ErrorOutlineIcon sx={{ color: "red" }} />
                  ) : showPassword ? (
                    <VisibilityOff sx={{ color: "white" }} />
                  ) : (
                    <Visibility sx={{ color: "white" }} />
                  )}
                </IconButton>
              </InputAdornment>
            ),
          }}
          {...register("password")}
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
            Submit
          </Button>
        </Box>
        <Box sx={{ marginTop: 1, width: "100%" }}>
          <p className="sign-in">Don't have an account? <br /><span onClick={()=> setIsSignup(true)}>sign up</span></p>
        </Box>
      </Stack>
    </Box>
  );
};
