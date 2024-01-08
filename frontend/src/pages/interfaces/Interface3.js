import EmployeeTable from '@/components/EmployeeTable';
import JobSummaryTable from '@/components/JobSummaryTable';


const Interface3 = ({employeeData, jobSummariesData}) => {
    return (
        <div className="w-full flex flex-col justify-center space-y-6">
            <p className='text-center text-4xl '>Csv Parser</p>
            <div className=' flex flex-row space-x-4 items-center w-full justify-center'>
                <button className='bg-cyan-600 p-4 py-2 rounded-md text-white'>Upload</button>
                <button className='bg-orange-300 p-4 py-2 rounded-md text-white'>Process</button>
            </div>
            <EmployeeTable employeeData={employeeData}/>
            <JobSummaryTable jobSummariesData={jobSummariesData}/>
        </div>
    );
};
export default Interface3;