import { Editor } from "@monaco-editor/react";
import { FC, useState } from "react";

export const Code: FC = () => {
  const [code, setCode] = useState("// Write your code here...");

  const handleEditorChange = (value: string | undefined) => {
    setCode(value || "");
  };

  return (
    <div
      style={{
        height: "100vh", 
        width: "100vw", 
        margin: 0, 
        padding: 0, 
        overflow: "hidden",
      }}
    >
      <Editor
        height="100%" 
        width="100%" 
        defaultLanguage="javascript" 
        defaultValue="// Write your code here..."
        value={code} 
        onChange={handleEditorChange} 
        theme="vs-dark" 
        options={{
          fontSize: 26,
          lineHeight: 26, 
          minimap: { enabled: false }, // Disable minimap if needed
        }}
      />
    </div>
  )
};
