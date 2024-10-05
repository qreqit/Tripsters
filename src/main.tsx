import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.scss'
import { RouteManager } from './components/RouteManager.tsx'
import './i18n.ts';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <RouteManager/>
  </StrictMode>,
)
