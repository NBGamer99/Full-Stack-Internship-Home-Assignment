import {Inter} from 'next/font/google'
import Interface1 from './interfaces/Interface1';

const inter = Inter({subsets: ['latin']})

export default function Home() {
    return (
        <main
            className={`flex min-h-screen flex-col items-center justify-between p-8 ${inter.className}`}
        >
            <Interface1/>
        </main>
    )
}