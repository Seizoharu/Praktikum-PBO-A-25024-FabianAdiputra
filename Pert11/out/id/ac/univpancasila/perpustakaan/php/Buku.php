<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

final class Buku
{
    public function __construct(
        public string $isbn,
        public string $judul
    ) {
    }
}
