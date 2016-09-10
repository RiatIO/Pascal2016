/* Det er lov aa ha alle mulige rare ASCII-tegn i kommentarer:
   !"#%&/?'". */

program UlovligTegn;
var x: integer;
begin
   write('!', '#', '%', '&', '/', '?', '"', '''', eol);

   x := 127 % 4;
   write(x, eol);
end.
