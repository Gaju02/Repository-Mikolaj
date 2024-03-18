import React, { useState, useEffect } from 'react';
const AddInvoiceForm = ({ addInvoice }) => {
  const [formData, setFormData] = useState({
    kupujący: '',
    sprzedający: '',
    numer_faktury: '',
    data_wystawienia: '',
    miejscowosc_wystawienia: '',
    termin_Zaplaty: '',
    Nazwa_uslugi: '',
    ilosc: '',
    jednostka: '',
    cena_netto: '',
    stawka_VAT: '',
    kwota_VAT: '',
    cena_brutto: '',
    czy_oplacone: '',
    sposob_zaplaty: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addInvoice(formData);
    setFormData({
      kupujący: '',
      sprzedający: '',
      numer_faktury: '',
      data_wystawienia: '',
      miejscowosc_wystawienia: '',
      termin_Zaplaty: '',
      Nazwa_uslugi: '',
      ilosc: '',
      jednostka: '',
      cena_netto: '',
      stawka_VAT: '',
      kwota_VAT: '',
      cena_brutto: '',
      czy_oplacone: '',
      sposob_zaplaty: ''
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      <label>
        Kupujący:
        <input
          type="text"
          name="kupujący"
          value={formData.kupujący}
          onChange={handleChange}
        />
      </label>
      {/* Pozostałe pola formularza */}
      <button type="submit">Dodaj</button>
    </form>
  );
};

const App = () => {
  const [invoices, setInvoices] = useState([]);

  useEffect(() => {
    const fetchInvoices = async () => {
      try {
        const response = await fetch('http://localhost:8081/api/v1/faktura');
        const data = await response.json();
        setInvoices(data);
      } catch (error) {
        console.log('Wystąpił błąd podczas pobierania faktur:', error);
      }
    };

    fetchInvoices();
  }, []);

  const addInvoice = async (invoiceData) => {
    try {
      const response = await fetch('http://localhost:8081/api/v1/faktura', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(invoiceData)
      });
      const data = await response.json();
      setInvoices((prevInvoices) => [...prevInvoices, data]);
    } catch (error) {
      console.log('Wystąpił błąd podczas dodawania faktury:', error);
    }
  };

  return (
    <div>
      <h1>Witaj, to jest wersja 1.0 aplikacji do faktur 2023</h1>
      <button onClick={() => setAddingInvoice(true)}>Dodaj fakturę</button>
      {addingInvoice ? (
        <AddInvoiceForm addInvoice={addInvoice} />
      ) : (
        <div>
          {invoices.map((invoice) => (
            <div key={invoice.id}>
              {/* Wyświetlanie danych faktury */}
              <hr />
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default App;