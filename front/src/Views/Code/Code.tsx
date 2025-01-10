import { Editor } from "@monaco-editor/react";
import { FC, useState } from "react";
import { AppBar, Box, Button, MenuItem, Select, SelectChangeEvent, Toolbar } from "@mui/material";
import { progLangs } from "./data";
import { Bolt } from "@mui/icons-material";

export const Code: FC = () => {
  const [code, setCode] = useState("// Write your code here...");
  const [progLang, setProgLang] = useState<{ name: string; img: string } | undefined>(progLangs[0]);

  const handleEditorChange = (value: string | undefined) => {

    setCode(value || "");
  };

  const handleLangChange = (event: SelectChangeEvent) => {
    setProgLang(progLangs.find((currProgLang) => currProgLang.name === event.target.value as string));
  };

  return (
    <Box
      sx={{
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        backgroundColor:"rgba(0,0,0,0.1)"
      }}
    >
      <AppBar
        sx={{
          backgroundColor: "transparent",
          boxShadow: "none",
          position: "static",
          background: "rgba(58, 47, 143, 0.8)",
          display: "flex",
          alignItems: { dm: "center", md: "start" },
          flexDirection: { md: "row", sm: "column" },
        }}
      >
        <Toolbar sx={{ justifyContent: "start", gap: 15, width: "50%" }}>
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
                    {currProgLang.name}{" "}
                    <img style={{ marginLeft: "3px" }} height="20px" src={currProgLang.img} alt="" />
                  </Box>
                </MenuItem>
              ))}
            </Select>
          </Box>
          <Button sx={{ padding: "2" }} color="success" variant="contained" endIcon={<Bolt />}>
            Run
          </Button>
        </Toolbar>
      </AppBar>
      <Box
  sx={{
    flexGrow: 1,
    display: "flex", // Enable flexbox for equal width
    flexDirection: "row", // Align children side by side
    height: "100%",
    gap:'15px'
  }}
>
  <Box
    sx={{
      flex: 1, // Take equal width
    }}
  >
    <Editor
      height="100%" // Fill parent container height
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
      alignItems: "center",
      justifyContent: "center", 
  backgroundColor:'red'
    }}
  >
    secondBox
  </Box>
</Box>

    </Box>
  );
};
