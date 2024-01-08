const ProcessButton = ({onProcess, disabled}) => {
    return (
        <button className='bg-orange-300 p-4 py-2 rounded-md text-white' onClick={onProcess} disabled={disabled}>
            Process
        </button>

    );
};

export default ProcessButton;