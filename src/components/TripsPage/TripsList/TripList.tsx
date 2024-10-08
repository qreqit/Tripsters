import { TripInfo } from "./TripInfo/TripInfo"
import pic from '../../../pics/home-about2.jpg'
import './TripList.scss'

export const TripsList = () => {
  return (
    <div className="triplist">
      <TripInfo trip={{
    name: 'tripname',
    startPoint: 'Kyiv',
    finishPoint: 'London',
    startDate: '01.01.2024',
    finishDate: '15.01.2024',
    aditionalPoints: ['Warsaw', 'Berlin', 'Madrid'], 
    members: [],
    link: '',
    owner: {
      name: 'Reaffith',
      dateOfBirth: '11.04.2006',
      id: 1,
      profilePic: pic,
      trips: [],
      friends: [],
    },
    status: 'completed',
  }} />
    </div>
  )
}