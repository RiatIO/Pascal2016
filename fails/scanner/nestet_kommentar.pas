/* Det er lov Ã¥ ha kommentarer utenpÃ¥ hverandre,
   om de er av ulik type,
   for eksempel slik: {indre kommentar}.

   Men det er ikke lov nÃ¥r de er av samme type,
   for eksempel slik: /*indre kommentar*/
      som _ikke_ er lov. Da betraktes denne linjen som vanlig program og skal gi en feilmelding siden '_' er ulovlig i annet enn char-literaler.
*/

program NesteteKommentarer;
begin
   write('K', eol);
end.
