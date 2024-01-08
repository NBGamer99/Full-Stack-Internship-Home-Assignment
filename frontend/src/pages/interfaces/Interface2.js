import {useState} from 'react';
import UploadButton from '@/components/UploadButton';
import ProcessButton from '@/components/ProcessButton';
import Interface3 from './Interface3';

const Interface2 = ({selectedFile}) => {
    const [file, setFile] = useState(selectedFile);
    const [navigateToInterface3, setNavigateToInterface3] = useState(false);
    const [employeeData, setEmployeeData] = useState([]);
    const [jobSummariesData, setJobSummariesData] = useState([]);

    const handleFileSelect = (newFile) => {
        setFile(newFile);
    };

    const handleProcess = async () => {
        if (file) {
            try {
                const formData = new FormData();
                formData.append('file', file);

                const response = await fetch(`http://localhost:8080/api/v1/upload`, {
                    method: 'POST',
                    body: formData,
                });

                if (!response.ok) {
                    throw new Error('File upload failed');
                }

                const data = await response.json();

                setEmployeeData(data.employees);
                setJobSummariesData(data.jobSummaries);

                if (data && data.employees && data.employees.length > 0) {
                    setNavigateToInterface3(true);
                }
            } catch (error) {
                console.error("Failed to upload file:", error);
            }
        }
    };

    if (employeeData && employeeData.length > 0 && navigateToInterface3) {
        return <Interface3 employeeData={employeeData} jobSummariesData={jobSummariesData}/>;
    }

    return (
        <div className="w-full flex flex-col justify-center  space-y-6">
            <p className='text-center text-4xl '>Csv Parser</p>

            <div className=' flex flex-row space-x-4 items-center w-full justify-center'>
                <UploadButton onFileSelect={handleFileSelect}/>
                <ProcessButton onProcess={handleProcess} disabled={!file}/>
            </div>

            <div className='text-center text-xl'>{selectedFile && <p>File selected: {selectedFile.name}</p>}</div>
        </div>
    );
};

export default Interface2;
