import {Table} from 'antd';

const EmployeeTable = ({employeeData}) => {

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: 'JobTitle',
            dataIndex: 'jobTitle',
            key: 'jobTitle',
        },
        {
            title: 'Salary',
            dataIndex: 'salary',
            key: 'salary',
        },
    ];


    return (
        <div className='w-[85%] m-auto'>
            <h1 className='text-center text-2xl mb-4'>List of Employees</h1>
            <Table className='shadow-md ' rowKey="id" dataSource={employeeData} columns={columns}
                   pagination={{pageSize: 4}}/>
        </div>
    );
};

export default EmployeeTable;
