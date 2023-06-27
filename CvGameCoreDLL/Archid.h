#pragma once

#ifndef ARCHID_MOD_H
#define ARCHID_MOD_H

// name of the Python module where all the BUG functions that the DLL calls must live
// MUST BE A BUILT-IN MODULE IN THE ENTRYPOINTS FOLDER
// currently CvAppInterface
#define PYBugModule      PYCivModule

#define KMOD_NAME        L"K-Mod"
#define KMOD_VERSION     L"1.46"

#define MOD_NAME         L"A-Mod"
#define MOD_VERSION      L"0.1"
#define MOD_BUILD        L"1"

#define TGA_RELIGIONS		GC.getTGA_RELIGIONS()
#define TGA_CORPORATIONS	GC.getTGA_CORPORATIONS()

#endif