import {useState} from 'react';
import UploadButton from '@/components/UploadButton';
import Interface2 from './Interface2';

const Interface1 = () => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [navigateToInterface2, setNavigateToInterface2] = useState(false);

    const handleFileSelect = (file) => {
        setSelectedFile(file);
        setNavigateToInterface2(true);
    };

    if (navigateToInterface2) {
        return <Interface2 selectedFile={selectedFile}/>;
    }

    return (
        <div className="w-full flex flex-col justify-center  space-y-6">
            <p className='text-center text-4xl '>Csv Parser</p>
            <div className=' flex flex-row space-x-4 items-center w-full justify-center'>
                <UploadButton onFileSelect={handleFileSelect}/>
            </div>
        </div>
    );
};

export default Interface1;