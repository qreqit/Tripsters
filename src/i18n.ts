import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

// Import your translation files
import en from './lang/en.json';
import ua from './lang/ua.json';

// Define a type for the resources
const resources = {
  en: { translation: en },
  ua: { translation: ua }
} as const; // 'as const' ensures that the object structure is deeply readonly.

i18n
  .use(initReactI18next) // Pass the i18n instance to react-i18next
  .init({
    resources,
    lng: 'en', // Set the default language
    fallbackLng: 'en', // Fallback to English if a translation is missing
    interpolation: {
      escapeValue: false // React already escapes values
    }
  });

export default i18n;
