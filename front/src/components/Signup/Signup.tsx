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
import { EmailOutlined, Visibility, VisibilityOff } from "@mui/icons-material";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";
import "./Signup.css";
import { useAuth } from "../../contexts/AuthContext";

const signupSchema = Yup.object().shape({
  username: Yup.string()
    .required("Username is required")
    .max(100, "reached chars limit"),
  password: Yup.string()
    .required("Password is required")
    .max(100, "reached chars limit"),
  email: Yup.string()
    .required("Email is required")
    .matches(
      /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
      "Invalid email format"
    )
});

interface SignupData {
  username: string;
  password: string;
  email: string;
}

interface SignupProps {
  setIsSignup: Dispatch<SetStateAction<boolean>>
}

export const Signup: FC<SignupProps> = ({ setIsSignup }) => {
  const [showPassword, setShowPassword] = useState(false);
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<SignupData>({
    resolver: yupResolver(signupSchema),
  });
  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };
  const { signup } = useAuth();

  const onSubmit = async (data: SignupData) => {
    if (await signup(data)) {
    setIsSignup(false);
    }
    reset();
  }

  return (
    <Box
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
      sx={{
        display: "flex",
        justifyContent: "center",
        marginBottom:'5px'
      }}
      width={"95%"}
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
            variant="h5"
            sx={{
              fontWeight: "bold",
              color: "black",
              fontFamily: '"Comic Sans MS", "Comic Sans", cursive',
              overflow: "hidden",

            }}
          >
            Sign up
          </Typography>
        </Box>

        <TextField
          label="Username"
          error={!!errors.username}
          helperText={errors.username ? errors.username.message : ""}
          type="text"
          variant="standard"
          sx={{
            width: '100%',
            "& .MuiInputLabel-root": { color: "black" },
            "& .MuiInputBase-root": { color: "black" },
            "& .MuiInput-underline:before": { borderBottomColor: "black" },
            "& .MuiInput-underline:hover:before": {
              borderBottomColor: "black",
            },
            "& .MuiInput-underline:after": { borderBottomColor: "black" },
          }}
          {...register("username")}
          InputProps={{
            endAdornment: errors.username ? (
              <ErrorOutlineIcon sx={{ color: "red" }} />
            ) : (
              <InputAdornment position="end">
                <IconButton edge="end" aria-label="username tag">
                  <PersonIcon sx={{ color: "black" }}></PersonIcon>
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
            width: '100%',
            "& .MuiInputLabel-root": { color: "black" },
            "& .MuiInputBase-root": { color: "black" },
            "& .MuiInput-underline:before": { borderBottomColor: "black" },
            "& .MuiInput-underline:hover:before": {
              borderBottomColor: "black",
            },
            "& .MuiInput-underline:after": { borderBottomColor: "black" },
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
                    <VisibilityOff sx={{ color: "black" }} />
                  ) : (
                    <Visibility sx={{ color: "black" }} />
                  )}
                </IconButton>
              </InputAdornment>
            ),
          }}
          {...register("password")}
        />

        <TextField
          label="Email"
          error={!!errors.email}
          helperText={errors.email ? errors.email.message : ""}
          type="text"
          variant="standard"
          sx={{
            width: '100%',
            "& .MuiInputLabel-root": { color: "black" },
            "& .MuiInputBase-root": { color: "black" },
            "& .MuiInput-underline:before": { borderBottomColor: "black" },
            "& .MuiInput-underline:hover:before": {
              borderBottomColor: "black",
            },
            "& .MuiInput-underline:after": { borderBottomColor: "black" },
          }}
          {...register("email")}
          InputProps={{
            endAdornment: errors.username ? (
              <ErrorOutlineIcon sx={{ color: "red" }} />
            ) : (
              <InputAdornment position="end">
                <IconButton edge="end" aria-label="username tag">
                  <EmailOutlined sx={{ color: "black" }} />
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
            Submit
          </Button>
        </Box>
        <Box sx={{ width: "100%" }}>
          <p className="sign-up">Already have an account? <br /><span onClick={() => setIsSignup(false)}>sign in</span></p>
        </Box>
      </Stack>
    </Box>
  );
};
