import {Table} from 'antd';

const JobSummaryTable = ({jobSummariesData}) => {
    const columns = [
        {
            title: 'Job Title',
            dataIndex: 'jobTitle',
            key: 'jobTitle',
        },
        {
            title: 'Average Salary',
            dataIndex: 'averageSalary',
            key: 'salary',
        },
    ];


    return (
        <div className='w-[55%] m-auto'>
            <h1 className='text-center text-2xl mb-4'>Job Summary</h1>
            <Table className='shadow-md' rowKey="jobTitle" dataSource={jobSummariesData} columns={columns}
                   pagination={{pageSize: 4}}/>
        </div>
    );
};

export default JobSummaryTable;
