import {useState} from 'react';

const UploadButton = ({onFileSelect}) => {
    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
        onFileSelect(event.target.files[0]);
    };

    return (
        <span className="bg-cyan-600 p-4 py-2 rounded-md text-white">
            <input type="file" id="file-input" onChange={handleFileChange} className={"hidden"}/>
              <label id="file-input-label" htmlFor="file-input"
              >Upload</label
              >
          </span>
    );
};

export default UploadButton;